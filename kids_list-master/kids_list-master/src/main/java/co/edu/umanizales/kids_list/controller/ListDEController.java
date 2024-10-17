package co.edu.umanizales.kids_list.controller;

import co.edu.umanizales.kids_list.model.Kid;
import co.edu.umanizales.kids_list.model.NodeDE;
import co.edu.umanizales.kids_list.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;

    // Obtener la lista de niños
    @GetMapping
    public NodeDE getListChildren() {
        return listDEService.showKids();
    }

    // Agregar un niño al final de la lista
    @PostMapping
    public String addKidToFinal(@RequestBody Kid kid) {
        listDEService.getListDE().add(kid);
        return "Adicionado exitosamente";
    }

    // Agregar un niño al inicio de la lista
    @PostMapping("/tostart")
    public String addKidToStart(@RequestBody Kid kid) {
        listDEService.getListDE().addToStart(kid);
        return "Adicionado exitosamente al inicio";
    }

    // Agregar un niño en una posición específica
    @PostMapping("/addatposition")
    public String addKidAtPosition(@RequestBody Kid kid, @RequestParam int position) {
        try {
            listDEService.getListDE().addInPosition(kid, position);
            return "El niño fue agregado a la posición " + position + " correctamente";
        } catch (IndexOutOfBoundsException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Invertir la lista
    @PostMapping("/invert")
    public String invertList() {
        listDEService.getListDE().invert();
        return "Lista invertida exitosamente";
    }

    // Eliminar un niño por posición
    @DeleteMapping("/deleteByPosition")
    public String deleteByPosition(@RequestParam int position) {
        try {
            listDEService.getListDE().deleteByPosition(position);
            return "El niño en la posición " + position + " ha sido eliminado";
        } catch (IndexOutOfBoundsException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Eliminar un niño por ID
    @DeleteMapping("/deletebyid/{id}")
    public String deleteKidById(@PathVariable String id) {
        listDEService.getListDE().deleteByID(id);
        return "Niño con ID " + id + " eliminado exitosamente";
    }

    // Intercalar niños por género
    @GetMapping("/mixbygender")
    public String mixByGender() {
        listDEService.getListDE().mixByGender();
        return "Niños intercalados por género exitosamente";
    }
}
