const fileInput = document.querySelector("#fileInput");
// fileInput : change 이벤트 걸기
// checkExtension() 호출
// 이미지 파일이라면 formData 객체 생성 후 append
function checkExtension(fileName) {
  // 정규식 사용
  const regex = /(.*?).(png|gif|jpg)$/;

  // 이미지 파일 => true, 그 외 파일 => false
  console.log(regex.test(fileName));

  return regex.text(fileName);
}

fileInput.addEventListener("change", (e) => {
  const files = e.target.files;
  const formData = new FormData();

  for (let index = 0; index < files.length; index++) {
    if (checkExtension(files[index].name)) {
      formData.append("uploadFiles", files[index]);
    }
  }

  for (const value of formData.values()) {
    console.log(value);
  }
});
