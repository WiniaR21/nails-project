document.getElementById("loginButton").addEventListener("click", function () {
    // Pobieranie wartości pól email i password
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Tworzenie obiektu z danymi do wysłania
    const loginData = {
        email: email,
        password: password
    };

    // Wysłanie danych do serwera
    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            // Obsługa odpowiedzi serwera
            console.log("Token:", data.token);
            console.log("Expiration Date:", data.expirationDate);

            // Możesz np. zapisać token w localStorage:
            localStorage.setItem("authToken", data.token);
        })
        .catch(error => {
            console.error("There was a problem with the fetch operation:", error);
        });
});