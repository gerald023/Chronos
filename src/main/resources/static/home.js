function startClock() {
    // setInterval(updateClock, 1000);  
    setInterval(addTime, 1000);
    setInterval(updateAllForeignTime, 1000);
    setInterval(getAllForeignTime, 1000);
    // setInterval(getForeignTime, 1000)
    // getFromDb()
    // getForeignTime()
//   setInterval(updateAllForeignTime, 1000);
//   setInterval(getForeignTime, 1000);
}
document.addEventListener("DOMContentLoaded", startClock)


function addTime() {
    axios.put("/api/localTime")
    .then(response => {
        document.getElementById('newTimer').innerText = response.data;
    })
    .catch(error => {
        document.getElementById('newTimer').innerText = 'Error fetching newTimer';
        console.error('There was an error fetching the newTimer!', error);
    });
}
addTime();
function getLocalTime() {
    axios.put("/api")
    .then(response => {
        document.getElementById('timer').innerText = response.data;
        console.log(response);
    })
    .catch(error => {
        document.getElementById('timer').innerText = 'Error fetching local time';
        console.error('There was an error fetching the timer!', error);
    });
    
}


function getForeignTime() {
    const selector = document.getElementById("selector").value

    const url = `/api/foreignTime/${selector}`;
    axios.put(url)
    .then(response => {
        // window.location.reload();
        updateAllForeignTime()
        console.log(response.data)
        console.log("foreign time")
    //          document.getElementById('foreignTime').innerHTML += `
    //     <div class="newTimes">
    //         <h1 th:text="">${response.data}</h1>
    //     </div>
    // `;
        console.log(response);
       
    })
    .catch(error => {
        // document.getElementById('foreignTime').innerHTML = 'Error fetching foreignTime';
        console.error('There was an error fetching the foreignTime!', error);
    });
    
    window.location.reload();
}
getForeignTime()

const updateAllForeignTime = ()=>{
    const url = `/api/updateAllForeignTime`

    axios.put(url)
    .then(res =>{
        console.log(res.data);
        document.getElementById("addTime").innerHTML = `
        <div class="foreignClock">
        <h1 th:text="${res.data.currentRegionTime}"></h1>
        <p th:text="${res.data.region}"></p>
    //   </div>`;
    window.location.reload();
    }).catch(err =>{
        console.log(err)
    })
}

updateAllForeignTime();


const getAllForeignTime = ()=>{
    const url = `/api/allForeign`

    axios.get(url)
    .then(res =>{
        console.log(res);
        document.getElementById("addTime").innerHTML = `
        <div class="foreignClock">
        <h1 th:text="${res.data.currentRegionTime}"></h1>
        <p th:text="${res.data.region}"></p>
      </div>`;
    }).catch(err =>{
        console.log(err)
    })
}

// function updateClock() {
//     var now = new Date();
//     var hours = now.getHours();
//     var minutes = now.getMinutes();
//     var seconds = now.getSeconds();
//     let amPm = hours > 11 ? "pm" : "am";
//     document.getElementById('clock').textContent =
//         (hours < 10 ? '0' : '') + hours + ':' +
//         (minutes < 10 ? '0' : '') + minutes + ':' +
//         (seconds < 10 ? '0' : '') + seconds +
//         amPm;
// }

async function fetchDate() {
    try {
        const response = await fetch('/api/date');
        if (response.ok) {
            const date = await response.text();
            document.getElementById('date').innerText = date;
        } else {
            document.getElementById('date').innerText = 'Error fetching date';
        }
    } catch (error) {
        document.getElementById('date').innerText = 'Error fetching date';
    }
}


document.addEventListener("click", function () {
    async function updateTimes() {
       try{
            const response =  fetch('/api/updateAllForeignTime')
            const data = await (await response).text()
      
                console.log(data)

       }catch (error){
         console.error('Error fetching times:', error)
       }
    }

    // Initial update
    updateTimes();

    // Update every second
    setInterval(updateTimes, 1000);
})