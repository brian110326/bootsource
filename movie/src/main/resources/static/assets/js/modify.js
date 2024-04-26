// 삭제 버튼 클릭
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (!confirm("삭제하시겠습니까?")) {
    return;
  }

  actionForm.submit();
});
