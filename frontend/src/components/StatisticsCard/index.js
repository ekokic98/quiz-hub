import { useEffect, useState } from 'react';
import { Bar, Line, Pie } from "react-chartjs-2";

const StatisticsCard = ({data}) => {

    const [allTimeData, setAllTimeData] = useState({});
    const [thisYearData, setThisYearData] = useState({});
    const [graphData, setGraphData] = useState({});

    useEffect(() => {
        setAllTimeData(getAllTimeData());
        setThisYearData(getThisYearData());
        setGraphData(getGraphData());
    }, []);

    const getAllTimeData = () => {
        let time = 0;
        let points = 0;
        let correctAnswers = 0;
        for (let i = 0; i < data.length; i++) {
            time += data[i].totalTime;
            correctAnswers += data[i].correctAnswers;
            points += data[i].points;
        }
        return {
            quizzes: data.length,
            time: time,
            points: points,
            correctAnswers: correctAnswers
        };
    }

    const getThisYearData = () => {
        let time = 0;
        let points = 0;
        let correctAnswers = 0;
        for (let i = 0; i < data.length; i++) {
            if (data[i].dateScored.substr(0, 4) === new Date().getFullYear().toString()) {
                time += data[i].totalTime;
                correctAnswers += data[i].correctAnswers;
                points += data[i].points;
            }
        }
        return {
            quizzes: data.length,
            time: time,
            points: points,
            correctAnswers: correctAnswers
        };
    }

    const getGraphData = () => {
        let quizzesByMonth = Array(12).fill(0);
        let pointsByMonth = Array(12).fill(0);
        let totalTimeByMonth = Array(12).fill(0);
        for (let i = 0; i < data.length; i++) {
            if (data[i].dateScored.substr(5, 2) === "01") {
                quizzesByMonth[0]++;
                pointsByMonth[0] += data[i].points;
                totalTimeByMonth[0] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "02") {
                quizzesByMonth[1]++;
                pointsByMonth[1] += data[i].points;
                totalTimeByMonth[1] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "03") {
                quizzesByMonth[2]++;
                pointsByMonth[2] += data[i].points;
                totalTimeByMonth[2] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "04") {
                quizzesByMonth[3]++;
                pointsByMonth[3] += data[i].points;
                totalTimeByMonth[3] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "05") {
                quizzesByMonth[4]++;
                pointsByMonth[4] += data[i].points;
                totalTimeByMonth[4] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "06") {
                quizzesByMonth[5]++;
                pointsByMonth[5] += data[i].points;
                totalTimeByMonth[5] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "07") {
                quizzesByMonth[6]++;
                pointsByMonth[6] += data[i].points;
                totalTimeByMonth[6] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "08") {
                quizzesByMonth[7]++;
                pointsByMonth[7] += data[i].points;
                totalTimeByMonth[7] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "09") {
                quizzesByMonth[8]++;
                pointsByMonth[8] += data[i].points;
                totalTimeByMonth[8] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "10") {
                quizzesByMonth[9]++;
                pointsByMonth[9] += data[i].points;
                totalTimeByMonth[9] += data[i].totalTime;
            } else if (data[i].dateScored.substr(5, 2) === "11") {
                quizzesByMonth[10]++;
                pointsByMonth[10] += data[i].points;
                totalTimeByMonth[10] += data[i].totalTime;
            } else {
                quizzesByMonth[11]++;
                pointsByMonth[11] += data[i].points;
                totalTimeByMonth[11] += data[i].totalTime;
            }
        }
        return [quizzesByMonth, pointsByMonth, totalTimeByMonth];
    }

    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
        "November", "December"];

    const getRandomLightColor = () => {
        let letters = 'BCDEF'.split('');
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * letters.length)];
        }
        return color;
    }

    const msToTime = (s) => {
        let ms = s % 1000;
        s = (s - ms) / 1000;
        let secs = s % 60;
        s = (s - secs) / 60;
        let min = s % 60;
        let hrs = (s - min) / 60;
        return hrs + ':' + min + ':' + secs + '.' + ms;
    }

    const generateChartData = (label, data, backgroundColor, borderColor) => {
        return {
            labels: months,
            datasets: [
                {
                    label: label,
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor,
                    borderWidth: 1,
                }
            ]
        };
    }

    const options = {
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true,
                    },
                },
            ],
        },
    };

    return (
        <div>
            <h3> ALL TIME: </h3>
            <p> Quizzes played: {allTimeData.quizzes} </p>
            <p> Time played: {msToTime(allTimeData.time)} </p>
            <p> Total points: {allTimeData.points}</p>
            <p> Total correct answers: {allTimeData.correctAnswers}</p>
            <hr/>
            <h3> THIS YEAR: </h3>
            <p> Quizzes played: {thisYearData.quizzes} </p>
            <p> Time played: {msToTime(thisYearData.time)} </p>
            <p> Total points: {thisYearData.points}</p>
            <p> Total correct answers: {thisYearData.correctAnswers}</p>
            <hr/>
            <Bar
                data={generateChartData('Quizzes played', graphData[0], '#DDE5E7', '#67727E')}
                options={options}
            />
            <hr/>
            <Line
                data={generateChartData('Points gained', graphData[1], '#D4674C', '#67727E')}
                options={options}
            />
            <hr/>
            <p> Time spent</p>
            <Pie
                data={generateChartData('Time spend', graphData[2], Array(12).fill().map(() => getRandomLightColor()), '#67727E')}
                options={options}
            />
        </div>
    );
}

export default StatisticsCard;
