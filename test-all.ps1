param(
    [switch]$SkipTests
)

$ErrorActionPreference = "Stop"

Set-Location $PSScriptRoot

$mvnArgs = @("clean", "verify")
if ($SkipTests) {
    $mvnArgs += "-DskipTests"
}

Write-Host "Running Maven build across shipment integration monorepo..."
mvn @mvnArgs
