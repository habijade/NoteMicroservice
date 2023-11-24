package com.nnk.springboot.services;

import com.nnk.springboot.exceptions.NoteNotFoundException;
import com.nnk.springboot.exceptions.PatientNotFoundException;
import com.nnk.springboot.modeles.Note;

import java.util.List;

public interface NoteService {

    public Note createNote(Note note) throws PatientNotFoundException;

    public List<Note> getNotes();

    public Note getById(String id) throws NoteNotFoundException;


    public Note updateNote(String id, Note noteDetails) throws NoteNotFoundException, PatientNotFoundException ;

    public String deleteNote(String id);
    void deleteNoteByPatientId(Integer patientId);


}
