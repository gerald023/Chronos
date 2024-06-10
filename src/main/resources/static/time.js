
const OPEN_CAGE_API_KEY = "d9e2c4f0c5374eddb67173602a2667c0";


// Function to get the user's current location
function getUserLocation() {
    const countryElement = document.getElementById('country');

    // Check if geolocation is available
    if (navigator.geolocation) {
        // Fetch the current position
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const { latitude, longitude } = position.coords;
                fetchCountry(latitude, longitude);
            },
            (error) => {
                countryElement.innerHTML = `Error: ${error.message}`;
            },
            {
                enableHighAccuracy: true,
                timeout: 5000,
                maximumAge: 0
            }
        );
    } else {
        countryElement.innerHTML = "Geolocation is not supported by this browser.";
    }
}

// Function to fetch the country using reverse geocoding
function fetchCountry(latitude, longitude) {
    const countryElement = document.getElementById('country');
    const url = `https://api.opencagedata.com/geocode/v1/json?q=${latitude}+${longitude}&key=${OPEN_CAGE_API_KEY}`;

    axios.get(url)
        .then(response => {
            const results = response.data.results;
            if (results.length > 0) {
                const country = results[0].components.country;
                countryElement.innerHTML = `${country}`;
                newCountry = country;
            } else {
                countryElement.innerHTML = "Country not found.";
            }
        })
        .catch(error => {
            countryElement.innerHTML = 'Error fetching country';
            console.error('There was an error fetching the country!', error);
        });
}

// Initialize the location fetching process
document.addEventListener('DOMContentLoaded', getUserLocation);

