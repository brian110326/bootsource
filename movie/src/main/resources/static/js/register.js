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

// form submit 기능 중지
// uploadResult ul li 태그 가져오기
document.querySelector("#register-form").addEventListener("submit", (e) => {
  e.preventDefault();

  // 첨부파일 정보 수집
  const attachInfos = document.querySelectorAll(".uploadResult ul li");

  console.log(attachInfos);

  // 수집된 정보를 form 태그 자식으로 붙여넣기
  const form = e.target;
  let result = "";
  attachInfos.forEach((obj, idx) => {
    // 3개의 hidden => MovidDto 객체 하나로 변경
    // name 정하는 규칙 존재
    result += `<input type="hidden" value="${obj.dataset.path}" name="movieImageDtos[${idx}].path" />`;
    result += `<input type="hidden" value="${obj.dataset.uuid}" name="movieImageDtos[${idx}].uuid" />`;
    result += `<input type="hidden" value="${obj.dataset.name}" name="movieImageDtos[${idx}].imgName" />`;
  });

  form.insertAdjacentHTML("beforeend", result);

  console.log(form.innerHTML);

  form.submit();
});
