package clean.domain.entities

sealed class Product(val name: String) {

    class WordProcessor(name: String) : Product(name)

    class Spreadsheet(name: String) : Product(name)

    class Database(name: String) : Product(name)
}