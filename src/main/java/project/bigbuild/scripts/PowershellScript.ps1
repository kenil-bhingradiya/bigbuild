param (
    [Parameter(Mandatory=$true)]
    [string] $name,
    [Parameter(Mandatory=$true)]
    [string] $givenName,
    [Parameter(Mandatory=$true)]
    [string] $surname,
    [Parameter(Mandatory=$true)]
    [string] $samAccName,
    [Parameter(Mandatory=$true)]
    [string] $passwd,
    [Parameter(Mandatory=$true)]
    [string] $department,
    [Parameter(Mandatory=$true)]
    [string] $displayName,
    [Parameter(Mandatory=$true)]
    [string] $email
)

New-ADUser `
-Name $name `
-GivenName $givenName `
-Surname $surname `
-SamAccountName $samAccName `
-AccountPassword $(ConvertTo-SecureString $passwd -AsPlainText -Force) `
-Company "TAP Project" `
-Department $department `
-EmailAddress $email `
-Enabled $true

Add-ADGroupMember -Identity ADKgroup -Members $samAccName

New-Item -Path $sharedFolder -ItemType Directory

$Acl = Get-Acl $sharedFolder
$permission = New-Object System.Security.AccessControl.FileSystemAccessRule("$samAccName", "FullControl", "ContainerInherit, ObjectInherit", "None", "Allow")
$Acl.AddAccessRule($Permission)
Set-Acl -Path $sharedFolder -aclObject $Acl

Set-ADUser -Identity $samAccName -HomeDirectory PATH -HomeDrive Z:


echo "User Created Successfully!!"