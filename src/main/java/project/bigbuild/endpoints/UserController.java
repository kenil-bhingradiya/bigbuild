package project.bigbuild.endpoints;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/create/newUser")
    public String createNewADUser(@RequestBody NewUserRequest newUser) throws IOException
    {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\project\\bigbuild\\scripts\\PowershellScript.ps1";
        String command = String.format("powershell.exe %s -name '%s' -givenName '%s' -surname '%s' -samAccName '%s' -passwd %s -department '%s' -displayName '%s' -email '%s'", path, newUser.getName(), newUser.getGivenName(), newUser.getSurname(), newUser.getSamAccName(), newUser.getPasswd(), newUser.getDepartment(), newUser.getDisplayName(), newUser.getEmail());
        System.out.println(command);
        Process proc = Runtime.getRuntime().exec(command);
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String data = "", res = "";
        while((data = reader.readLine()) != null)
        {
            System.out.println(res);
            res = data;
        }
        reader.close();
        proc.getOutputStream().close();
        System.out.println(res);
        return res;
    }

    @GetMapping("/deleteUser/{username}")
    public Boolean deleteUser(@PathVariable("username") String username) throws IOException
    {
        String command = String.format("powershell.exe Remove-ADUser -Identity %s -Confirm:$false", username);
        System.out.println(command);
        Process proc = Runtime.getRuntime().exec(command);

        proc.getOutputStream().close();
        return true;
    }
}
