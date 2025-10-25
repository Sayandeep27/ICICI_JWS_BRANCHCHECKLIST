let branches = [];

async function loadBranches() {
  const token = localStorage.getItem("jwt");
  if (!token) return window.location.href = "index.html";

  const res = await fetch("/api/branches", {
    headers: { Authorization: `Bearer ${token}` },
  });
  branches = await res.json();

  const dropdown = document.getElementById("branchDropdown");
  dropdown.innerHTML = `<option value="">Select Branch</option>`;
  branches.forEach(b => {
    dropdown.innerHTML += `<option value="${b.id}">${b.branchName}</option>`;
  });
}

function updateSolId() {
  const selectedId = parseInt(document.getElementById("branchDropdown").value);
  const branch = branches.find(b => b.id === selectedId);
  document.getElementById("solId").value = branch ? branch.solId : "";
}

function goToChecklist() {
  const selectedId = document.getElementById("branchDropdown").value;
  if (!selectedId) return alert("Please select a branch first");

  localStorage.setItem("selectedBranchId", selectedId);
  window.location.href = "checklist.html";
}

loadBranches();
