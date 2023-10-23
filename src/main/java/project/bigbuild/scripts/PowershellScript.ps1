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
    [string] $displayName
)

echo "creating user!"

New-ADUser `
-Name $name `
-GivenName $givenName `
-Surname $surname `
-SamAccountName $samAccountName `
-AccountPassword $(ConvertTo-SecureString $passwd -AsPlainText -Force) `
-Company "TAP Project" `
-Department $department `
-Enabled $true

echo "user created"