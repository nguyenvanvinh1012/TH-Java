$(document).ready(function () {
    $.ajax( {
        url: 'http://localhost:8080/api/v1/books',
        type: 'GET',
        dataType: 'json',
        success: function (data){
            let trHTML = '';
            $.each(data, function (i, item) {
                trHTML = trHTML + '<tr id="book-' + item.id + '">' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.title + '</td>' +
                    '<td>' + item.author + '</td>' +
                    '<td>' + item.price + '</td>' +
                    '<td>' + item.categoryName + '</td>' +
                    '<td sec:authorize="hasAnyAuthority(\'ADMIN\')">' +
                    '<a href="/books"' + item.id + ' class="text-primary" onclick="apiEditBook('+item.id+',this);return false;">Edit</a> | ' +
                    '<a href="/books"' + item.id + ' class="text-danger" onclick="apiDeleteBook(' + item.id + ', this); return false;">Delete</a>' +
                    '</td>' +
                    '</tr>';
            });
            $('#book-table-body').append(trHTML);
        }
    });
});

function apiDeleteBook(id) {
    if (confirm('Are you sure you want to delete this book?'))
        $.ajax({
            url: 'http://localhost:8080/api/v1/books/' + id,
            type: 'DELETE',
            success: function (){
                alert('Book deleted successfully!');
                $('#book-' + id).remove();
            }
        });
}
function apiCreateBook() {

    var title = $('#new-book-title').val();
    var author = $('#new-book-author').val();
    var price = $('#new-book-price').val();
    var categoryName = $('#new-book-category').val();

    var newBook = {
        title: title,
        author: author,
        price: price,
        categoryName: categoryName
    };

    $.ajax({
        url: 'http://localhost:8080/api/v1/books',
        type: 'POST',
        data: JSON.stringify(newBook),
        contentType: 'application/json',
        success: function(response) {
            alert('Book created successfully! Book ID: ' + response.id);

            // Clear the input fields after successful creation
            $('#new-book-title').val('');
            $('#new-book-author').val('');
            $('#new-book-price').val('');
            $('#new-book-category').val('');

            // Append the new book to the table
            // var trHTML = '<tr id="book-' + response.id + '">' +
            //     '<td>' + response.id + '</td>' +
            //     '<td>' + response.title + '</td>' +
            //     '<td>' + response.author + '</td>' +
            //     '<td>' + response.price + '</td>' +
            //     '<td>' + response.categoryName + '</td>' +
            //     '<td sec:authorize="hasAnyAuthority(\'USER\')">' +
            //     '<a href="/books"' + response.id + ' class="text-primary" onclick="apiEditBook(' + response.id + ', this); return false;">Edit</a> | ' +
            //     '<a href="/books"' + response.id + ' class="text-danger" onclick="apiDeleteBook(' + response.id + ', this); return false;">Delete</a>' +
            //     '</td>' +
            //     '</tr>';
            //
            // $('#book-table-body').append(trHTML);
        }
    });
}