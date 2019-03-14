
function displayBooks(books) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in books) {
        var book = books[i];
        var $row = $('<tr>');
        $('<td>').html(book.title).appendTo($row);
        $('<td>').html(book.author.name).appendTo($row);
        $('<td>').html(book.genre).appendTo($row);
        $('<td>').html(book.price).appendTo($row);
        $('<td>').html(book.quantity).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshBooks() {
    $.get('/books', {}, function(result) {
        displayBooks(result);
    });
}

function addBook(book) {
    $.ajax('/createBooks', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(book),
        dataType: 'json',
        success: function(result) {
            $('#error').val(result.message);
            refreshBooks();
            $('#title, #genre, #price, #quantity').val('');
        }
    });
}

function deleteBook(id) {
     $.ajax('/deleteBooks', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(id),
            dataType: 'json',
            success: function() {
                refreshBooks();
                $('#title, #genre, #price, #quantity').val('');
            }
        });
}


function updateBook(book) {
     $.ajax('/updateBooks', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(book),
            dataType: 'json',
            success: function(result) {
                $('#error').val(result.message);
                refreshBooks();
                $('#title, #genre, #price, #quantity').val('');
            }
     });
}


function generateCSVReport(report) {
    $.ajax('/generateReport', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(report),
        dataType: 'json',
        success: function(result) {
            $('#error').val(result.message);
            refreshBooks();
        }
    });
}

function generatePDFReport(report) {
    $.ajax('/generateReport', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(report),
        dataType: 'json',
        success: function(result) {
            $('#error').val(result.message);
            refreshBooks();
        }
    });
}

function googleSearch(searchBy) {
    $.get('/googleSearch', {searchBy:searchBy}, function(result) {
            $('#error').val(result.message);
            displayBooks(result);
            $('#searchBy').val('');
        });
}



$(function() {
    refreshBooks();
    $('button').click(function() {
        if (this.id == "createBook") {
            addBook({
                'title':     $('#title').val(),
                'authorId':   $('#author').val(),
                'genre':     $('#genre').val(),
                'price':    $('#price').val(),
                'quantity':     $('#quantity').val()
            });
        };
        if (this.id == "deleteBook") {
            deleteBook(
                $('#bookTitle').val()
            )
        };
        if (this.id == "updateBook") {
            updateBook({
                'title':     $('#updateTitle').val(),
                'authorId':   $('#updateAuthor').val(),
                'genre':     $('#updateGenre').val(),
                'price':    $('#updatePrice').val(),
                'quantity':     $('#updateQuantity').val()
            })};
        if (this.id == "generateCsvReport") {
            generateCSVReport("CSV");
        };
        if (this.id == "generatePdfReport") {
            generatePDFReport("PDF");
        };
        if (this.id == "googleSearch") {
            googleSearch($('#searchBy').val());
        };
        return false;
    });
});