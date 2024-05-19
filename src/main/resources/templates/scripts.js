document.addEventListener("DOMContentLoaded", function () {
  // Example of a Pie Chart for Expenses by Group
  var ctx = document.getElementById("expenses-by-group").getContext("2d");
  var expensesByGroupChart = new Chart(ctx, {
    type: "pie",
    data: {
      labels: ["Giving", "Wellness", "Auto", "Food", "Debt", "Discretionary"],
      datasets: [
        {
          data: [5.21, 8, 12.39, 19.16, 22.03, 30.95],
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56",
            "#4BC0C0",
            "#9966FF",
            "#FF9F40",
          ],
        },
      ],
    },
    options: {
      responsive: true,
    },
  });

  // You can add more charts in a similar way
});
