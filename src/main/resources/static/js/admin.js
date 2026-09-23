// Admin shared interactions: delete confirmation modal
function ensureModal(){
  if(document.getElementById("delete-modal")) return;
  const div = document.createElement("div");
  div.innerHTML = `
    <div class="modal-backdrop" id="delete-modal">
      <div class="modal">
        <h3>Delete Blog?</h3>
        <p>Are you sure you want to delete "<span id="delete-target"></span>"? This action cannot be undone.</p>
        <div class="modal-actions">
          <button class="btn btn-outline" onclick="closeDeleteModal()">Cancel</button>
          <button class="btn btn-danger" onclick="closeDeleteModal()">Delete</button>
        </div>
      </div>
    </div>`;
  document.body.appendChild(div);
}
function openDeleteModal(title){
  ensureModal();
  document.getElementById("delete-target").textContent = title;
  document.getElementById("delete-modal").classList.add("open");
}
function closeDeleteModal(){
  const m = document.getElementById("delete-modal");
  if(m) m.classList.remove("open");
}
