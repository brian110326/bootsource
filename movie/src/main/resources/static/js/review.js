// 날짜 포맷 변경 함수
const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};

// /reviews/99/all 요청 처리
const reviewsLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      // 화면단에 리뷰 총개수 변경
      const reviewCnt = document.querySelector(".review-cnt");
      reviewCnt.innerHTML = data.length;
      const reviewList = document.querySelector(".reviewList");
      if (data.length > 0) {
        reviewList.classList.remove("hidden");
      }

      let result = "";
      data.forEach((review) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="${review.reviewNo}">`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<div><span class="font-semibold">${review.text}</span></div>`;
        result += `<div class="small text-muted">`;
        result += `<span class="d-inline-block mr-3">${review.nickname}</span>평점 : ${review.grade}점<span class="grade"></span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(review.createdDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        // 작성자 == 로그인사용자
        if (`${review.email}` == user) {
          result += `<div class="mb-2">`;
          result += `<button class="btn btn-outline-danger btn-sm">삭제</button>`;
          result += `</div><div>`;
          result += `<button class="btn btn-outline-success btn-sm">수정</button>`;
          result += `</div>`;
        }

        result += `</div></div>`;
      });

      reviewList.innerHTML = result;
    });
};

reviewsLoaded();

// 리뷰 등록 or 수정
// 리뷰 폼 submit 중지
// text, grade, mid, mno
const reviewForm = document.querySelector(".review-form");
reviewForm.addEventListener("submit", (e) => {
  e.preventDefault();

  // 수정이라면 reviewNo가 존재
  const reviewNo = reviewForm.querySelector("#reviewNo");
  const email = reviewForm.querySelector("#email");

  const data = {
    text: document.querySelector("#text").value,
    mid: document.querySelector("#mid").value,
    mno: mno,
    grade: grade || 0,
    reviewNo: reviewNo.value,
    email: email.value,
  };

  if (!reviewNo.value) {
    // 새 review 등록
    fetch(`/reviews/${mno}`, {
      method: "post",
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },

      body: JSON.stringify(data),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);

        const text = document.querySelector("#text");
        const mid = document.querySelector("#mid");
        const nickname = document.querySelector("#nickname");

        // 리뷰작성후 입력값 초기화
        text.value = "";

        // grade = 0;
        reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();

        if (data) {
          alert("댓글 등록 성공");
        }

        reviewsLoaded();
      });
  } else {
    // 수정
    fetch(`/reviews/${mno}/${reviewNo.value}`, {
      method: "put",
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);

        const text = document.querySelector("#text");
        const mid = document.querySelector("#mid");
        const nickname = document.querySelector("#nickname");

        // 리뷰작성후 입력값 초기화
        text.value = "";
        reviewNo.value = "";
        // grade = 0;
        reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();

        if (data) {
          alert("댓글 수정 성공");
        }

        reviewsLoaded();
      });
  }
});

// 삭제 클릭시 reviewNo 가져오기
document.querySelector(".reviewList").addEventListener("click", (e) => {
  console.log(e.target);

  const target = e.target;

  // reviewNo 가져오기
  const reviewNo = target.closest(".review-row").dataset.rno;
  // 컨트롤러에서 작성자와 로그인유저가 같은지 다시 한번 확인
  const email = reviewForm.querySelector("#email");

  if (target.classList.contains("btn-outline-danger")) {
    if (!confirm("리뷰를 삭제하시겠습니까?")) {
      return;
    }

    const form = new FormData();
    form.append("email", email.value);

    fetch(`/reviews/${mno}/${reviewNo}`, {
      method: "delete",
      headers: {
        "X-CSRF-TOKEN": csrfValue,
      },
      body: form,
    })
      .then((response) => response.text())
      .then((data) => {
        alert(data + " 번 리뷰 삭제");
        reviewsLoaded();
      });
  } else if (target.classList.contains("btn-outline-success")) {
    // 수정 버튼을 누른다면
    const reviewForm = document.querySelector(".review-form");

    fetch(`/reviews/${mno}/${reviewNo}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        // form 안에있는 것은 다 가져오기
        reviewForm.querySelector("#reviewNo").value = data.reviewNo;
        reviewForm.querySelector("#mid").value = data.mid;
        reviewForm.querySelector("#nickname").value = data.nickname;
        reviewForm.querySelector("#text").value = data.text;
        reviewForm.querySelector("#email").value = data.email;
        reviewForm.querySelector(".starrr a:nth-child(" + data.grade + ")").click();
        reviewForm.querySelector("button").innerHTML = "리뷰 수정";
      });
  }
});
