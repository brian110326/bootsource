// 제목을 클릭 시 a태그 기능 중지
// data-id에 있는 값 가져오기

document.querySelector("tbody").addEventListener("click", (e) => {
  // a태그 중지시켰기 때문에 read.html로 넘어가지 않음
  e.preventDefault();

  const target = e.target;

  console.log(target.dataset.id);

  fetch(`http://localhost:8080/read/${target.dataset.id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      // 디자인 영역 가져오기
      document.querySelector("#category").value = data.categoryName;
      document.querySelector("#title").value = data.title;
      document.querySelector("#publisher").value = data.publisherName;
      document.querySelector("#writer").value = data.writer;
      document.querySelector("#price").value = data.price;
      document.querySelector("#salePrice").value = data.salePrice;
      document.querySelector("#book_id").value = data.id;
    });
});

// 삭제 클릭 시 id 가져오기
document.querySelector(".btn-primary").addEventListener("click", (e) => {
  e.preventDefault();

  const id = document.querySelector("#book_id").value;

  console.log(id);

  // /delete/{id} + Delete
  fetch(`/delete/${id}`, { method: "delete" })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("삭제성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});

// 수정
document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  e.preventDefault();

  const myForm = document.querySelector("#myForm");

  const book_id = document.querySelector("#book_id").value;

  const data = {
    price: document.querySelector("#price").value,
    salePrice: document.querySelector("#salePrice").value,
    id: book_id,
  };

  console.log(data);

  // method 지정 안하면 get방식으로 전송
  fetch(`/modify/${book_id}`, {
    method: "put",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), // JSON.stringify() : javascript 객체 ==> JSON 형태로 변환
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("수정 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
