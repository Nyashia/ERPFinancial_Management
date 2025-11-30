// Initialize dashboard
document.addEventListener('DOMContentLoaded', function() {
    updateCurrentDate();
    initializeCharts();
    setupEventListeners();
});

// Update current date display
function updateCurrentDate() {
    const now = new Date();
    const options = { 
        weekday: 'long', 
        year: 'numeric', 
        month: 'long', 
        day: 'numeric' 
    };
    document.getElementById('current-date').textContent = 
        now.toLocaleDateString('en-US', options);
}

// Initialize chart placeholders (would be replaced with actual chart library)
function initializeCharts() {
    console.log('Initializing charts...');
    // This would integrate with Chart.js, D3.js, or other charting libraries
}

// Setup event listeners
function setupEventListeners() {
    // Period selector changes
    const periodSelectors = document.querySelectorAll('.period-selector');
    periodSelectors.forEach(selector => {
        selector.addEventListener('change', function() {
            updateChartData(this.value);
        });
    });

    // Mobile menu toggle (if needed)
    setupMobileMenu();
}

function updateChartData(period) {
    console.log('Updating chart data for period:', period);
    // This would make AJAX calls to update chart data
}

function setupMobileMenu() {
    // Add mobile menu functionality if needed
    const menuToggle = document.createElement('button');
    menuToggle.innerHTML = '<i class="fas fa-bars"></i>';
    menuToggle.className = 'mobile-menu-toggle';
    menuToggle.style.display = 'none';
    
    document.querySelector('.content-header').prepend(menuToggle);
    
    // Show on mobile
    if (window.innerWidth <= 768) {
        menuToggle.style.display = 'block';
        menuToggle.addEventListener('click', toggleMobileMenu);
    }
}

function toggleMobileMenu() {
    const sidebar = document.querySelector('.sidebar');
    sidebar.style.transform = sidebar.style.transform === 'translateX(0px)' ? 
        'translateX(-100%)' : 'translateX(0px)';
}

// Simulate real-time data updates
setInterval(() => {
    // This would be replaced with actual WebSocket or AJAX calls
    const kpiCards = document.querySelectorAll('.kpi-card h3');
    kpiCards.forEach(card => {
        // Simulate small fluctuations in data
        const currentValue = parseFloat(card.textContent.replace(/[^0-9.]/g, ''));
        const fluctuation = (Math.random() - 0.5) * 1000;
        const newValue = Math.max(0, currentValue + fluctuation);
        card.textContent = '$' + Math.round(newValue).toLocaleString();
    });
}, 30000); // Update every 30 seconds