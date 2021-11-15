package api.restservice;

import domain.Batch;
import domain.BatchController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchAPI {

    private BatchController batchController = new BatchController();

    @GetMapping
    public Batch batch(@RequestParam(value="id", defaultValue = "1") float id) {
        id = batchController.getBatchId();
        return new Batch(id);
    }

}
