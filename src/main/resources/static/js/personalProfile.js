// POPUP PAGE START
const openverificationBtn = document.querySelector(".open-verification-modal");
const closeBtn = document.querySelectorAll(".close-modal");
const verificationModal = document.querySelector(".dialog");

openverificationBtn.addEventListener("click", () => {
  verificationModal.showModal();
});

closeBtn.forEach((close) => {
  close.addEventListener("click", () => {
    verificationModal.close();
  });
});
// POPUP PAGE END

// RESPONSIVE TEXTAREA START
const profileTextarea = document.querySelector(".edit-info");
profileTextarea.addEventListener("input", autoResize1, false);

function autoResize1() {
  profileTextarea.style.height = "300px";
  profileTextarea.style.height = profileTextarea.scrollHeight + "px";
}
// RESPONSIVE TEXTAREA END
