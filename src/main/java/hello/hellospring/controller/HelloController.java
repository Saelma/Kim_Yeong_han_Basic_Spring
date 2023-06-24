package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "hello";
    }

    /* 실행하고자 할땐 localhost:8080/hello-mvc?name=spring!!! 이며, name에는 다른 글자가 들어가도 됨
    * templates 폴더에서 hello-template 파일을 찾고, name값에따라 hello뒤에 name이 온다
    * name은 정해져 있지 않기 때문에 name=spring!!! 와 같이, name을 미리 정해줄 수 있다*/
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    /* ResponseBody를 사용하면 굳이 html 파일을 만들지 않아도 ResponseBody에 내장된 html형식으로 변환됨 */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /* 아래와 같이 객체를 반환하고 ResponseBody가 있으면 아래와 같은 json 형식이다.*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
    private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
