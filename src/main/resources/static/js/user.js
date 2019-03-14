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

function processSale(sale){
    $.ajax('/sellBook', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(sale),
            dataType: 'json',
            success: function(result) {
                if (result.errorCode == 404) {
                    $('#error').val(result.message);
                } else {
                    $('#totalSum').val(result);
                    $('#error').val('');
                }
                refreshBooks();
            }
        });
}

function searchByTitle(title) {
    $.get('/searchByTitle', {title:title}, function(result) {
        console.log(result);
        $('#error').val(result.message);
        displayBooks(result);
    });
}

function searchByAuthor(auth) {
    $.get('/searchByAuthor', {auth:auth}, function(result) {
        displayBooks(result);
    });
}

function searchByGenre(genre) {
    $.get('/searchByGenre',{genre:genre}, function(result) {
        displayBooks(result);
    });
}


$(function() {
    refreshBooks();
    $('button').click(function() {
        if (this.id == "sellBook") {
            processSale({
                'title':        $('#saleTitle').val(),
                'authorId':     $('#saleAuthor').val(),
                'quantity':     $('#saleQuantity').val()
            });
        };
        if (this.id == "titleSearch") {
            searchByTitle($('#title').val());
        };
        if (this.id == "genreSearch"){
            searchByGenre($('#genre').val());
        };
        if (this.id == "authorSearch") {
            searchByAuthor($('#auth').val());
        };
        return false;
    });
});