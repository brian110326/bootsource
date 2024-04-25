const searchForm = document.querySelector("#searchForm");

searchForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const type = document.querySelector("#type");
  const keyword = document.querySelector("#keyword");

  if (type.value == "") {
    alert("검색 조건을 선택해주세요");
    type.focus();
    return;
  } else if (keyword.value == "") {
    alert("검색어를 입력해주세요");
    keyword.focus();
    return;
  }

  e.target.submit();
});
