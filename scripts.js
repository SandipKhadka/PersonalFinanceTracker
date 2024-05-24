function toggleMenu() {
  var panel = document.getElementById("side-panel");
  if (panel.style.width === "250px") {
    panel.style.width = "0";
  } else {
    panel.style.width = "250px";
  }
}

function closeMenu() {
  document.getElementById("side-panel").style.width = "0";
}

function toggleUserMenu() {
  var userMenu = document.getElementById("user-menu");
  if (userMenu.style.display === "block") {
    userMenu.style.display = "none";
  } else {
    userMenu.style.display = "block";
  }
}

document.addEventListener("click", function (event) {
  var isClickInsideMenu = document
    .querySelector(".menu-container")
    .contains(event.target);
  var isClickInsideUserMenu = document
    .querySelector(".user-container")
    .contains(event.target);
  var isClickInsidePanel = document
    .getElementById("side-panel")
    .contains(event.target);

  if (!isClickInsideMenu && !isClickInsidePanel) {
    document.getElementById("side-panel").style.width = "0";
  }

  if (!isClickInsideUserMenu) {
    document.getElementById("user-menu").style.display = "none";
  }
});
