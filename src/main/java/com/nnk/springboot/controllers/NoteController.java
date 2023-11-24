package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.NoteNotFoundException;
import com.nnk.springboot.exceptions.PatientNotFoundException;
import com.nnk.springboot.modeles.Note;
import com.nnk.springboot.services.NoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public Note createNote(@Valid @RequestBody Note note) throws PatientNotFoundException {
        LOGGER.info("note créer avec succée: {}", note);
        return noteService.createNote(note);
    }

    @GetMapping("/note")
    public List<Note> getNotes() {
        LOGGER.info("la list des notes trouvée");
        return noteService.getNotes();
    }

    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable String id) throws NoteNotFoundException {
        LOGGER.info("la note est trouvée via son :{}", id);
        return noteService.getById(id);
    }

    @PutMapping("note/update/{id}")
    public Note updateNote(@Valid @PathVariable String id, @RequestBody Note note) throws NoteNotFoundException, PatientNotFoundException {

        LOGGER.info("Mise à jour de la note : {}", id);
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("note/{id}")
    public String deleteNote(@PathVariable String id) {
        LOGGER.info("la note est supprimer via le :{}", id);
        return noteService.deleteNote(id);
    }
    @DeleteMapping("note/patient/{patientId}")
    public void deleteNoteByPatientId(@PathVariable Integer patientId) {
        LOGGER.info("la note du patient est supprimée :{}", patientId);
        noteService.deleteNoteByPatientId(patientId);
    }
}
