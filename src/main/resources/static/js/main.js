// RESPONSIVE SIDEBAR START
const navLinks = document.querySelectorAll(".nav-text");
const windowPathname = window.location.pathname;

navLinks.forEach((navLinks) => {
  if (navLinks.href.includes(windowPathname)) {
    navLinks.classList.add("active");
  }
});
// RESPONSIVE SIDEBAR END

// TOGGLE SIDE BAR START
const menu = document.querySelector(".fa-bars");
const side = document.querySelector(".main-content-left");
const side2 = document.querySelector(".side-bar");

menu.addEventListener("click", () => {
  side.classList.toggle("hide");
  side2.classList.toggle("hide");
});

// TOGGLE SIDE BAR END

// RWD START
if (window.innerWidth < 976) {
  side.classList.add("hide");
  side2.classList.add("hide");
}
// RWD END

//WINDOW ALERT START
function popup() {
  const postingCheck = document.querySelector(".report-textarea");

  if (postingCheck.value != "") {
    window.alert("檢舉成功 ! ");
  }
}

function reportProfilePop() {
  var profileCheck = document.getElementById("report-profile");

  if (profileCheck.checked == true) {
    window.alert("檢舉成功 ! ");
  } else {
    window.alert("檢舉失敗 ! ");
  }
}
//WINDOW ALERT END

// BUTTON CHANGE COLOR START
function toggleLikeBtn() {
  var toggleLike = document.querySelector(".fa-heart");

  if (toggleLike.style.color == "gray") {
    toggleLike.style.color = "#D93F3F";
  } else {
    toggleLike.style.color = "gray";
  }
}

function toggleSaveBtn() {
  var toggleSave = document.querySelector(".fa-bookmark");

  if (toggleSave.style.color == "gray") {
    toggleSave.style.color = "#F6D851";
  } else {
    toggleSave.style.color = "gray";
  }
}

function toggleProfileLikeBtn() {
  var toggleProfileLike = document.querySelector(".like-profile");

  if (toggleProfileLike.classList.contains("gray")) {
    toggleProfileLike.classList.remove("gray");
    toggleProfileLike.classList.add("blue");
  } else {
    toggleProfileLike.classList.remove("blue");
    toggleProfileLike.classList.add("gray");
  }
}
// BUTTON CHANGE COLOR END

//EDIT PROFILE START
function toggleEditPage() {
  var edit = document.getElementById("edit-info");
  var editBtn = document.getElementById("comfirm");

  edit.disabled = false;
  edit.style.backgroundColor = "#f7f6f2";

  editBtn.disabled = false;
  editBtn.style.visibility = "visible";
}

function closeEdit() {
  var edit = document.getElementById("edit-info");
  var editBtn = document.getElementById("comfirm");

  edit.disabled = true;
  edit.style.backgroundColor = "white";

  editBtn.disabled = true;
  editBtn.style.visibility = "hidden";
}
//EDIT PROFILE END

// SLIDER START
// MY SAVING SLIDER START
function showContent() {
  var myPostBtn = document.getElementById("my-post-radio");
  var mySavingBtn = document.getElementById("my-saving-radio");
  var myPostColor = document.querySelector(".select-post-btn span");
  var mySavingColor = document.querySelector(".select-saving-btn span");
  var style = document.querySelector(".left");

  if (myPostBtn.checked == true) {
    style.style.marginLeft = "0";
    document

      .querySelector(".select-saving-btn")
      .classList.remove("border-active");
    document.querySelector(".select-post-btn").classList.add("border-active");

    myPostColor.style.color = "black";
    document.querySelector(".select-saving-btn span").style.color = "gray";
  } else if (mySavingBtn.checked == true) {
    style.style.marginLeft = "-50%";

    document.querySelector(".select-saving-btn").classList.add("border-active");
    document
      .querySelector(".select-post-btn")
      .classList.remove("border-active");

    myPostColor.style.color = "gray";
    mySavingColor.style.color = "black";
  }
}
// MY SAVING SLIDER END

// INDEX SLIDER START
function showIndexContent() {
  var style = document.querySelector(".index-posting-left");

  var allPost = document.getElementById("all-post");
  var compulsory = document.getElementById("compulsory-post");
  var ge = document.getElementById("ge-post");
  var tracking = document.getElementById("tracking-post");

  var allPostBtn = document.querySelector(".select-all-btn");
  var compulsoryBtn = document.querySelector(".select-compulsory-btn");
  var geBtn = document.querySelector(".select-ge-btn");
  var trackingBtn = document.querySelector(".select-tracking-btn");

  var allColor = document.querySelector(".select-all-btn span");
  var compulsoryColor = document.querySelector(".select-compulsory-btn span");
  var geColor = document.querySelector(".select-ge-btn span");
  var trackingColor = document.querySelector(".select-tracking-btn span");

  if (allPost.checked == true) {
    style.style.marginLeft = "0";

    allPostBtn.classList.add("border-active");
    compulsoryBtn.classList.remove("border-active");
    geBtn.classList.remove("border-active");
    trackingBtn.classList.remove("border-active");

    allColor.style.color = "black";
    compulsoryColor.style.color = "gray";
    geColor.style.color = "gray";
    trackingColor.style.color = "gray";
  } else if (compulsory.checked == true) {
    style.style.marginLeft = "-25%";

    allPostBtn.classList.remove("border-active");
    compulsoryBtn.classList.add("border-active");
    geBtn.classList.remove("border-active");
    trackingBtn.classList.remove("border-active");

    allColor.style.color = "gray";
    compulsoryColor.style.color = "black";
    geColor.style.color = "gray";
    trackingColor.style.color = "gray";
  } else if (ge.checked == true) {
    style.style.marginLeft = "-50%";

    allPostBtn.classList.remove("border-active");
    compulsoryBtn.classList.remove("border-active");
    geBtn.classList.add("border-active");
    trackingBtn.classList.remove("border-active");

    allColor.style.color = "gray";
    compulsoryColor.style.color = "gray";
    geColor.style.color = "black";
    trackingColor.style.color = "gray";
  } else if (tracking.checked == true) {
    style.style.marginLeft = "-75%";

    allPostBtn.classList.remove("border-active");
    compulsoryBtn.classList.remove("border-active");
    geBtn.classList.remove("border-active");
    trackingBtn.classList.add("border-active");

    allColor.style.color = "gray";
    compulsoryColor.style.color = "gray";
    geColor.style.color = "gray";
    trackingColor.style.color = "black";
  }
}
//INDEX SLIDER END
// SLIDER END

// READ INPUT IMAGE NAME START
function readUrl(input) {
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = (e) => {
      let imgData = e.target.result;
      let imgName = input.files[0].name;
      input.setAttribute("data-title", imgName);
      console.log(e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
  }
}
// READ INPUT IMAGE NAME END
