// 삭제 버튼 클릭
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (!confirm("삭제하시겠습니까?")) {
    return;
  }

  //actionForm.action = "/movie/remove";
  actionForm.submit();
});

// X버튼 클릭시 이미지의 li 제거
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();

  console.log("e.target", e.target);
  console.log("e.currentTarget", e.currentTarget);

  const currentLi = e.target.closest("li");

  console.log(currentLi);

  if (!confirm("정말로 삭제하시겠습니까?")) {
    return;
  }

  currentLi.remove();
});
