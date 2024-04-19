// form submit 시 submit 기능 중지
// form 내용 가져오기 => javascript 객체로 생성
document.querySelector("#createForm").addEventListener("submit", (e) => {
  e.preventDefault();

  const data = {
    categoryName: document.querySelector("#categoryName").value,
    title: document.querySelector("#title").value,
    publisherName: document.querySelector("#publisherName").value,
    writer: document.querySelector("#writer").value,
    price: document.querySelector("#price").value,
    salePrice: document.querySelector("#salePrice").value,
  };

  console.log(data);

  // method 지정 안하면 get방식으로 전송
  fetch(`http://localhost:8080/book/new`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), // JSON.stringify() : javascript 객체 ==> JSON 형태로 변환
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("입력 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
