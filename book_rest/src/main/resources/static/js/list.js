// 제목을 클릭 시 a태그 기능 중지
// data-id에 있는 값 가져오기

document.querySelector("tbody").addEventListener("click", (e) => {
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
    });
});
