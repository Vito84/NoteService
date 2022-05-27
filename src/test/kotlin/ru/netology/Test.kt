package ru.netology

import org.junit.Test

import org.junit.Assert.*

internal class TestNote {

    @Test
    fun testAdd(){
        val ns = NotesService()
        ns.add("title", "text")
        ns.getById(0)

    }

    @Test
    fun testCreateComment(){
        val ns = NotesService()
        val expected = "title123"


        ns.add("title", "text")
        ns.add("title", "text")



        val comment = Comment(1,1,1,"new comment"
            , expected,null, null, null)

        ns.createComment(1,comment)


        val  actual = ns.getComments(1,0,1,1)[0].title
        assertEquals(actual, expected)
    }

    @Test
    fun testDeleteNote() {
        val ns = NotesService()


        ns.add("title", "text")
        ns.add("title", "text")
        ns.add("title", "text")
        ns.delete(0)


        assertEquals(ns.get().size, 2)

    }

    @Test
    fun testDeleteComment() {
        val ns = NotesService()
        val expected = "title123"


        ns.add("title", "text")
        ns.add("title", "text")



        val comment = Comment(
            1, 1, 1, "new comment", expected, null, null, null
        )

        ns.createComment(0, comment)
        ns.deleteComment(0,0)

        assertEquals(ns.getComments(0,0,100,1)[0].deleted,true)
    }


    @Test
    fun testEditNote() {
        val ns = NotesService()
        val expectedText = "text123"


        ns.add("title", "text")

        ns.edit (0, expectedText)


        val  actual  = ns.get()[0].text
        assertEquals(actual, expectedText)

    }


    @Test
    fun testEditComment() {
        val ns = NotesService()
        val expectedtitle = "title123"
        val expectedText = "text123"


        ns.add("title", "text")
        ns.add("title", "text")



        val comment = Comment(
            1, 1, 1, "title abc", "txt abc", null, null, null
        )

        ns.createComment(0, comment)
        ns.editComment(0,0,expectedtitle,expectedText)


        val  actual = ns.getComments(0,0,1,1)[0].title
        assertEquals(actual, expectedtitle)

        val  actual2 = ns.getComments(0,0,1,1)[0].message
        assertEquals(actual2, expectedText)

    }

    @Test
    fun testRestoreEditComment() {
        val ns = NotesService()
        val expectedtitle = "title123"
        val expectedText = "text123"


        ns.add("title", "text")
        ns.add("title", "text")



        val comment = Comment(
            1, 0, 1, "title abc", "txt abc", null, null, null
        )

        ns.createComment(0, comment)
        ns.deleteComment(0,0)
        ns.restoreComment(0,0)
        assertEquals(ns.getComments(0,0,0,0).size, 1)

    }
}