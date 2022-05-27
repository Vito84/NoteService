package ru.netology

class NotesService {

    private val items = mutableListOf<Note>()
    private var index: Int = 0

    fun add(title: String, text: String) {
        items.add(Note(index, 1, 1, title, text))
        index++
    }


    fun createComment(noteId: Int, comment: Comment) {
        items[noteId].comment.add(comment)
    }


    fun delete(noteId: Int) {
        items.removeAt(noteId)
    }


    fun deleteComment(noteId: Int, commentID: Int) {
        items[noteId].comment[commentID].deleted = true
    }


    fun editComment(noteId: Int, commentID: Int, title: String, text: String) {
        items[noteId].comment[commentID].message = text
        items[noteId].comment[commentID].title = title
    }


    fun edit(noteId: Int, text: String) {
        items[noteId].text = text
    }

    fun get(): MutableList<Note> {
        return items
    }

    fun getById(noteId: Int): Note {
        return items[noteId]
    }

    fun getComments(noteId: Int, offset: Int, count: Int, sort: Int): MutableList<Comment> {
        val itemsToReturn = mutableListOf<Comment>()

        for (n in offset..items[noteId].comment.size - 1) {
            itemsToReturn.add(items[noteId].comment[n])
        }
        return itemsToReturn
    }


    fun restoreComment(noteId: Int, comment_id: Int) {
        items[noteId].comment[comment_id].deleted = false
    }

}