package com.nnk.springboot.services.impl;

import com.nnk.springboot.exceptions.NoteNotFoundException;
import com.nnk.springboot.exceptions.PatientNotFoundException;
import com.nnk.springboot.modeles.Note;
import com.nnk.springboot.proxy.PatientClient;
import com.nnk.springboot.repositories.NoteRepository;
import com.nnk.springboot.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientClient patientClient;

    public Note createNote(Note note) throws PatientNotFoundException {
        if(patientClient.getPatient(Integer.valueOf(note.getPatientId())).isEmpty()){
            throw new PatientNotFoundException("patient with id " + note.getPatientId() + " not found");
        }
        note.setDate(LocalDate.now());
        note.setId(UUID.randomUUID().toString().substring(0, 5));
        return noteRepository.save(note);
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note getById(String id) throws NoteNotFoundException {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note non trouvé avec l'ID : " + id));
    }

    public Note updateNote(String id, Note noteDetails) throws NoteNotFoundException, PatientNotFoundException{
        if(patientClient.getPatient(Integer.valueOf(noteDetails.getPatientId())).isEmpty()){
            throw new PatientNotFoundException("patient with id " + noteDetails.getPatientId() + " not found");
        }
        return noteRepository.findById(id)
                .map(note -> {
                    note.setPatientId(noteDetails.getPatientId());
                    note.setDate(noteDetails.getDate());
                    note.setContent(noteDetails.getContent());
                    return noteRepository.save(note);

                }).orElseThrow(() -> new NoteNotFoundException("Note non trouvé avec l'ID :  " + id));
    }

    @Override
    public String deleteNote(String id) {
        noteRepository.deleteById(id);
        return "Note supprimé";
    }

    @Override
    public void deleteNoteByPatientId(Integer patientId) {
        noteRepository.deleteByPatientId(String.valueOf(patientId));
    }
}
