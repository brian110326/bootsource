const actionForm = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (!confirm("정말로 삭제하시겠습니까?")) {
    return;
  }

  actionForm.action = "/guestbook/delete";
  actionForm.submit();
});
