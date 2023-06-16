// POPUP PAGE START
const openReportPostingBtn = document.querySelector(".open-report-posting-btn");
const openReportCommentBtn = document.querySelector(
  ".open-report-comment-btn"
);
const closeBtn = document.querySelectorAll(".close-modal");
// const closeCommentBtn = document.querySelectorAll(".close-report-comment");
// const closePostingtBtn = document.querySelectorAll(".close-report-comment");
const reportPostingModal = document.querySelector(".report-posting");
const reportCommentModal = document.querySelector(".report-comment");

openReportPostingBtn.addEventListener("click", () => {
  reportPostingModal.showModal();
});

openReportCommentBtn.addEventListener("click", () => {
  reportCommentModal.showModal();
});


// openReportCommentBtn.forEach((open) => {
//   open.addEventListener("click", () => {
//     reportCommentModal.showModal();
//   });
// });

closeBtn.forEach((close) => {
  close.addEventListener("click", () => {
    reportPostingModal.close();
    reportCommentModal.close();
  });
});

// closeCommentBtn.addEventListener("click", () => {
//   reportCommentModal.showModal();
// });
// POPUP PAGE END

// RESPONSIVE TEXTAREA START
const reportTextarea = document.querySelector(".report-textarea");
reportTextarea.addEventListener("input", autoResize, false);

function autoResize() {
  reportTextarea.style.height = "300px";
  reportTextarea.style.height = reportTextarea.scrollHeight + "px";
}

const textarea = document.querySelector(".comment-textarea");
textarea.addEventListener("input", autoResize1, false);

function autoResize1() {
  textarea.style.height = "auto";
  textarea.style.height = textarea.scrollHeight + "px";
}
// RESPONSIVE TEXTAREA END

//COMMENT ORDER EXCHANGE COLOR START
const btnList = document.querySelectorAll(".comment-order-selection-btn");

btnList.forEach((btn) => {
  btn.addEventListener("click", () => {
    if (btn.classList.contains("inactive-btn")) {
      document.querySelector(".toggle-btn").classList.add("inactive-btn");
      document.querySelector(".toggle-btn").classList.remove("toggle-btn");

      btn.classList.remove("inactive-btn");
      btn.classList.add("toggle-btn");
    }
  });
});
//COMMENT ORDER EXCHANGE COLOR END

//LIKE BUTTON CHANGE COLOR START
var toggleCommentLike = document.querySelectorAll(".fa-thumbs-up");

toggleCommentLike.forEach((like) => {
  like.addEventListener("click", () => {
    if (like.classList.contains("gray")) {
      like.classList.remove("gray");
      like.classList.add("blue");
    } else {
      like.classList.remove("blue");
      like.classList.add("gray");
    }
  });
});
//LIKE BUTTON CHANGE COLOR END
