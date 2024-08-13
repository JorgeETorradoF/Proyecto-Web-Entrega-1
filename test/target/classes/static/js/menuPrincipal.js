document.addEventListener("DOMContentLoaded", function() { 
    var menu = document.querySelector("#menu");
    var menuOffset = menu.offsetTop;

    window.addEventListener("scroll", function() {
        if (window.pageYOffset > menuOffset) {
            menu.classList.add("fijo");
        } else {
            menu.classList.remove("fijo");
        }
    });

    var links = document.querySelectorAll("a");

    links.forEach(function(link) {
        link.addEventListener("mouseover", function() {
            this.style.color = "blue";
        });

        link.addEventListener("mouseout", function() {
            this.style.color = "";
        });
    });
});
