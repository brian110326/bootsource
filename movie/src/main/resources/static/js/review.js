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

      let result = "";
      data.forEach((review) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="1">`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<div><span class="font-semibold">${review.text}</span></div>`;
        result += `<div class="small text-muted">`;
        result += `<span class="d-inline-block mr-3">${review.nickname}</span>평점 : ${review.grade}점<span class="grade"></span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(review.createdDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        result += `<div class="mb-2">`;
        result += `<button class="btn btn-outline-danger btn-sm">삭제</button>`;
        result += `</div><div>`;
        result += `<button class="btn btn-outline-success btn-sm">수정</button>`;
        result += `</div></div></div>`;
      });

      reviewList.innerHTML = result;
    });
};

reviewsLoaded();

// 리뷰 등록
// 리뷰 폼 submit 중지
// text, grade, mid, mno
const reviewForm = document.querySelector(".review-form");
reviewForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const data = {
    text: document.querySelector("#text").value,
    mid: document.querySelector("#mid").value,
    mno: mno,
    grade: grade,
  };

  fetch(`/reviews/${mno}`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.text())
    .then((data) => {
      console.log(data);
      if (data) {
        alert("댓글 등록 성공");
      }
      reviewsLoaded();
    });
});
