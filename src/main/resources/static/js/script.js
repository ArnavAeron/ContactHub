console.log("Loading Script");

//                      CHANGING THE THEME OF THE PAGE 

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});


function changeTheme(){
    // set to webpage

    changePageTheme(currentTheme, currentTheme);

    document.querySelector('html').classList.add(currentTheme);

    // Setting the listner to change the theme of the website 
    const changeThemeButton = document.querySelector("#theme_change_button");
    changeThemeButton.addEventListener("click" , (event) => {
        const oldTheme = currentTheme;
        console.log("Change theme button clicked");
        if(currentTheme == "dark"){
            currentTheme = "light";
        }
        else{
            currentTheme = "dark";
        }


        // updating the localstorage
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);

        // Change the text of button 
        changeThemeButton.querySelector("span").textContent = currentTheme == "dark"? "Light" : "Dark";
    });
}

// Set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// Get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

// change page theme 
function changePageTheme(theme,oldTheme){
    // updating the localstorage
    setTheme(currentTheme);
    document.querySelector('html').classList.remove(oldTheme);
    document.querySelector('html').classList.add(currentTheme);

    // Change the text of button 
    document.querySelector("#theme_change_button").querySelector("span").textContent = currentTheme == "dark"? "Light" : "Dark";
}

//                          END OF THEME CHANGE PAGE 