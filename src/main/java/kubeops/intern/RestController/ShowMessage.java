package kubeops.intern.RestController;

import kubeops.intern.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class ShowMessage {
    @GetMapping("")
    public String ShowMessage(Message m1){
        return "Hello world I'M Pinjang";
    }
    @GetMapping("/Intern")
    public String ShowMessage2(Message m2){
        return "Hello KubeOps Intern";
    }
//    @GetMapping("/intern")


}
