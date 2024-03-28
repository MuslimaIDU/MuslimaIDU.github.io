


// Check if cached data exists in localStorage
const cachedWeatherData = localStorage.getItem('weatherData');

if (cachedWeatherData) {
    // If cached data exists, parse and use it directly
    const data = JSON.parse(cachedWeatherData);
    displayWeatherData(data);
} else {
    // Fetch data from the server
    fetch('http://localhost:8080/weather')
        .then(response => response.json())
        .then(data => {
            // Cache the fetched data
            localStorage.setItem('weatherData', JSON.stringify(data));
            // Handle the received JSON data
            displayWeatherData(data);
        })
        .catch(error => console.error('Error:', error));
}

function displayWeatherData(data) {
    let weatherDataDiv = document.getElementById('weatherData');
    weatherDataDiv.innerHTML = ''; // Clear existing data

    data.forEach(entry => {
        let city = entry.city;
        let temperature = entry.temperature;
        let humidity = entry.humidity;
        weatherDataDiv.innerHTML += `<p>City: ${city}, Temperature: ${temperature}, Humidity: ${humidity}</p>`;
    });
}
