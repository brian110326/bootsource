// X를 누르면 파일 삭제 요청
// a tag 기능 중지 => href값 가져와서 화면에 출력하기

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();

  console.log("e.target", e.target); // i태그
  console.log("e.currentTarget", e.currentTarget);

  const currentLi = e.target.closest("li");

  // data-file에 있는 값 가져오기
  const filePath = e.target.closest("a").dataset.file;
  console.log(filePath);

  const formData = new FormData();
  formData.append("filePath", filePath);

  fetch("/upload/remove", {
    method: "post",
    body: formData,
  })
    .then((response) => response.text())
    .then((data) => {
      console.log(data);

      if (data) {
        // 해당 li제거
        currentLi.remove();
      }
    });
});
