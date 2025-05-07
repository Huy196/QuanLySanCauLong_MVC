function openNav3() {

}

function handleSetting(select) {
    const value = select.value;
    if (value === "profile") {
        window.location.href = "/thong-tin-ca-nhan";
    } else if (value === "logout") {
        window.location.href = "/login/logout";
    }
    select.selectedIndex = 0;
}