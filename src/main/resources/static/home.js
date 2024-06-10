function startClock() {
    // setInterval(updateClock, 1000);  
    setInterval(addTime, 1000);
    // setInterval(getForeignTime, 1000)
    // getFromDb()
    // getForeignTime()
  
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
// function getFromDb() {
//     axios.get("/api/newTime/1")
//     .then(response => {
//         document.getElementById('fromDb').innerText = response.data;
//         console.log(response.data);
//     })
//     .catch(error => {
//         document.getElementById('fromDb').innerText = 'Error fetching local fromDb';
//         console.error('There was an error fetching the fromDb!', error);
//     });
    
// }




console.log(selector.value);


function getForeignTime() {
    // const selectedValue = selector.value;
    const selector = document.getElementById("selector").value

    const url = `/api/foreignTime/${selector}`;
    axios.put(url)
    .then(response => {
        document.getElementById('foreignTime').innerHTML += `
        <div class="newTimes">
            <h1 th:text="">${response.data}</h1>
        </div>
    `;
        console.log(response);
    })
    .catch(error => {
        document.getElementById('foreignTime').innerHTML = 'Error fetching foreignTime';
        console.error('There was an error fetching the foreignTime!', error);
    });
    
}
// getForeignTime()
// getForeignTime()
// selector.addEventListener("change", getForeignTime())

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
// async function fetchTime() {
// try {
//   const response = await fetch('/api/times');
//   if (response.ok) {
//       const time = await response.text();
//       document.getElementById('timer').innerText = time;
//   } else {
//       document.getElementById('timer').innerText = 'Error fetching time';
//   }
// } catch (error) {
//   document.getElementById('timer').innerText = 'Error fetching time';
// }
// }

// function updateTime() {
// fetchTime();
// fetchDate();
// setTimeout(updateTime, 1000);
// // Update every second
// }

// updateTime();