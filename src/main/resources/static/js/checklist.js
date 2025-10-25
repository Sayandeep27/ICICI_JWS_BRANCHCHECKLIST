let sections = []; 
let currentSectionData = null;

// ✅ Load available sections from DB
async function loadSections() {
  const token = localStorage.getItem("jwt");
  if (!token) return window.location.href = "index.html";

  // Fetch unique section list from SQL backend
  const res = await fetch(`/api/checklist/Outside`, { headers: { Authorization: `Bearer ${token}` }});
  if (res.ok) sections.push("Outside");

  const resIn = await fetch(`/api/checklist/Inside`, { headers: { Authorization: `Bearer ${token}` }});
  if (resIn.ok) sections.push("Inside");

  const resAtm = await fetch(`/api/checklist/ATM%20Lobby`, { headers: { Authorization: `Bearer ${token}` }});
  if (resAtm.ok) sections.push("ATM Lobby");

  const sectionDropdown = document.getElementById("sectionDropdown");
  sectionDropdown.innerHTML = `<option value="">Select Section</option>`;

  sections.forEach(sec => {
    sectionDropdown.innerHTML += `<option value="${sec}">${sec}</option>`;
  });
}

// ✅ Load categories from DB based on selected section
async function loadCategories() {
  const sectionName = document.getElementById("sectionDropdown").value;
  const token = localStorage.getItem("jwt");
  if (!sectionName) return;

  const res = await fetch(`/api/checklist/${encodeURIComponent(sectionName)}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

  currentSectionData = await res.json();

  const categoryDropdown = document.getElementById("categoryDropdown");
  categoryDropdown.innerHTML = `<option value="">Select Category</option>`;

  currentSectionData.categories.forEach(cat => {
    categoryDropdown.innerHTML += `<option value="${cat.title}">${cat.title}</option>`;
  });

  // Reset subItems
  document.getElementById("subItemDropdown").innerHTML = `<option value="">Select Sub Item</option>`;
}

// ✅ Load subItems when category selected
function loadSubItems() {
  const selectedCategory = document.getElementById("categoryDropdown").value;
  const subItemDropdown = document.getElementById("subItemDropdown");

  subItemDropdown.innerHTML = `<option value="">Select Sub Item</option>`;
  if (!selectedCategory || !currentSectionData) return;

  const categoryObj = currentSectionData.categories.find(c => c.title === selectedCategory);

  categoryObj?.subItems?.forEach(item => {
    subItemDropdown.innerHTML += `<option value="${item}">${item}</option>`;
  });
}

// ✅ Show popup for review
function showReviewPopup() {
  const section = document.getElementById("sectionDropdown").value;
  const category = document.getElementById("categoryDropdown").value;
  const subItem = document.getElementById("subItemDropdown").value;

  if (!section || !category || !subItem) {
    alert("Please select a section, category, and sub item.");
    return;
  }

  document.getElementById("reviewPopup").classList.remove("hidden");
}

// ✅ Close popup
function closePopup() {
  document.getElementById("reviewPopup").classList.add("hidden");
}

// ✅ Submit Review
async function submitReview() {
  const branchId = localStorage.getItem("selectedBranchId");
  const section = document.getElementById("sectionDropdown").value;
  const category = document.getElementById("categoryDropdown").value;
  const subItem = document.getElementById("subItemDropdown").value;
  const remarks = document.getElementById("remarks").value;
  const file = document.getElementById("photoUpload").files[0];

  if (!branchId || !section || !category || !subItem || !file) {
    alert("Please fill all fields and upload a photo.");
    return;
  }

  const reader = new FileReader();
  reader.onloadend = async () => {
    const photoBase64 = reader.result;

    const res = await fetch("/api/review", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
      body: JSON.stringify({
        branchId,
        section,
        category,
        subItem,
        remarks,
        photoBase64,
      }),
    });

    if (res.ok) {
      window.location.href = "success.html";
    } else {
      alert("Error submitting review");
    }
  };

  reader.readAsDataURL(file);
}

// ✅ Initialize on page load
document.addEventListener("DOMContentLoaded", loadSections);
