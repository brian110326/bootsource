// 페이지 로드시 무조건 실행
// 즉시 실행함수
// fetch() - 함수 작성 후 호출

// 함수 작성
// 1. function 함수명(){}
// 2. const(or let) mehod1 = () => {}

// 함수 실행 => 함수 호출하기
// method1();

// 호이스팅(선언하지 않고 먼저 호출 후 선언)
// 1번은 호이스팅 가능, 2번은 불가능

// var로 선언된 변수는 호이스팅 가능
// const, let은 호이스팅 불가능

// 날짜 포맷 변경 함수
const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};

// 댓글 목록 가져오기
const replyList = document.querySelector(".replyList");
const replyLoaded = () => {
  // 댓글목록 보여줄 영역 가져오기

  fetch(`/replies/board/${bno}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      // 댓글 총 개수 확인
      const span = document.querySelector(".d-inline-block");
      span.innerHTML = data.length;

      let result = "";
      data.forEach((reply) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom reply-row" data-rno="${reply.rno}">`;
        result += `<div class="p-3"><img src="/img/default.png" alt="" class="rounded-circle mx-auto d-block" style="width: 60px; height: 60px" /></div>`;
        result += `<div class="flex-grow-1 align-self-center"><div>${reply.replyer}</div>`;
        result += `<div><span class="fs-5">${reply.text}</span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(reply.createdDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        result += `<div class="mb-2"><button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        result += `<div class="mb-2"><button class="btn btn-outline-success btn-sm">수정</button></div>`;
        result += `</div></div>`;
      });
      // 영역에 result 보여주기
      replyList.innerHTML = result;
    });
};

// 함수호출
replyLoaded();

// 새 댓글 등록
// 새 댓글 등록 폼 submit 시
// submit 기능 중지/ 작성자/ 댓글 가져오기 => 스크립트 객체로 변경
const replyForm = document.querySelector("#replyForm");
replyForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const replyer = replyForm.querySelector("#replyer");
  const text = replyForm.querySelector("#text");

  // 수정인 경우
  const rno = replyForm.querySelector("#rno");

  const reply = {
    bno: document.querySelector("#bno").value,
    replyer: replyer.value,
    text: text.value,
    rno: rno.value,
  };

  if (!rno.value) {
    // rno가 없으면 새댓글이 등록
    fetch(`/replies/new`, {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          alert(data + "번 댓글 등록");

          // replyForm에 남아있는 내용 제거
          replyer.value = "";
          text.value = "";

          replyLoaded();
        }
      });
  } else {
    // rno가 있으면 수정을 하는 작업

    fetch(`/replies/${rno.value}`, {
      method: "put",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => response.text())
      .then((data) => {
        if (data) {
          alert("수정 성공");
          replyer.value = "";
          text.value = "";
          rno.value = "";
          replyLoaded();
        }
      });
  }

  //console.log(data);
});

// 이벤트 전파 : 자식요소에 일어난 이벤트는 상위요소에 전달
// 댓글 삭제/수정 버튼 클릭 시 이벤트 전파로 찾아오기
// rno 가져오기
replyList.addEventListener("click", (e) => {
  // 실제 이벤트가 일어난 대상 찾기 : e.target
  const btn = e.target;

  // closest("요소") : 제일 가까운 상위요소 찾기
  const rno = btn.closest(".reply-row").dataset.rno;

  console.log("rno", rno);

  // 삭제 or 수정 버튼이 눌러졌을 때에만 동작
  // 삭제 or 수정 버튼이 클릭 되었는지 여부로 구분
  if (btn.classList.contains("btn-outline-danger")) {
    fetch(`/replies/${rno}`, { method: "delete" })
      .then((response) => response.text())
      .then((data) => {
        if (data == "success") {
          alert("댓글 삭제 성공");
          replyLoaded();
        }
      });
  } else if (btn.classList.contains("btn-outline-success")) {
    // rno에 해당하는 댓글을 가져온 후
    // reply작성폼에 넣기
    fetch(`/replies/${rno}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        const replyer = replyForm.querySelector("#replyer");
        const text = replyForm.querySelector("#text");

        // replyForm.querySelector("")
        replyForm.querySelector("#rno").value = data.rno;
        replyer.value = data.replyer;
        text.value = data.text;
      });
  }
});
