$(document).ready(function() {
    // Sayfa yüklendiğinde tüm To-Do'ları getir ve listele
    loadToDos();

    // Yeni To-Do ekleme formu gönderildiğinde çalışır
    $('#todo-form').submit(function(event) {
        event.preventDefault();
        const description = $('#description').val();
        createToDo(description);
    });
});

// Tüm To-Do'ları yükler ve sayfada gösterir
function loadToDos() {
    $.ajax({
        url: '/api/todos',
        method: 'GET',
        success: function(todos) {
            $('#todo-list').empty();
            todos.forEach(function(todo) {
                $('#todo-list').append(`
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        ${todo.description}
                        <div>
                            <button class="btn btn-primary btn-sm" onclick="editToDo(${todo.id}, '${todo.description}')">Düzenle</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteToDo(${todo.id})">Sil</button>
                        </div>
                    </li>
                `);
            });
        }
    });
}

// Yeni bir To-Do oluşturur
function createToDo(description) {
    $.ajax({
        url: '/api/todos',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ description: description, completed: false }),
        success: function() {
            $('#description').val(''); // Formu temizle
            loadToDos(); // Listeyi güncelle
        }
    });
}

// Belirli bir To-Do'yu siler
function deleteToDo(id) {
    $.ajax({
        url: `/api/todos/${id}`,
        method: 'DELETE',
        success: function() {
            loadToDos(); // Listeyi güncelle
        }
    });
}

// Düzenleme modali açılır
function editToDo(id, description) {
    $('#edit-id').val(id);
    $('#edit-description').val(description);
    $('#editModal').modal('show');
}

// To-Do'yu günceller
function updateToDo() {
    const id = $('#edit-id').val();
    const description = $('#edit-description').val();

    $.ajax({
        url: `/api/todos/${id}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({ description: description, completed: false }),
        success: function() {
            $('#editModal').modal('hide');
            loadToDos(); // Listeyi güncelle
        }
    });
}
