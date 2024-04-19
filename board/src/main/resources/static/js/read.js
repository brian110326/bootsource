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
const replyList = () => {
  // 댓글목록 보여줄 영역 가져오기
  const replyList = document.querySelector(".replyList");

  fetch(`/replies/board/${bno}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

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
replyList();
