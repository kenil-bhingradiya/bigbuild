package project.bigbuild.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.bigbuild.endpoints.dtos.NewUserRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class UserController
{
    @GetMapping("/")
    public String helloMethod()
    {
        return "Hello from another device!!";
    }

    @GetMapping("/create/newUser")
    public String createNewADUser(@RequestBody NewUserRequest newUser) throws IOException
    {
        String command = String.format("powershell.exe C:\\PowershellScript.ps1 -name %s -givenName %s -surname %s -samAccName %s -passwd %s -department %s -displayName '%s'", newUser.getName(), newUser.getGivenName(), newUser.getSurname(), newUser.getSamAccName(), newUser.getPasswd(), newUser.getDepartment(), newUser.getDisplayName());
        Process proc = Runtime.getRuntime().exec(command);
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String data = "";
        System.out.println("reading data..");
        while ((data = reader.readLine()) != null)
        {
            System.out.println(data);
        }
        reader.close();
        proc.getOutputStream().close();
        return "user is created!";
    }
}
