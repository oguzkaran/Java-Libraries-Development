param(
    [int]$r = 1
)

$allPassed = $true
$checkMark = [char]0x2713
$crossMark = [char]0x2717

$stopwatch = [System.Diagnostics.Stopwatch]::StartNew()

Write-Host "Running 'mvn clean test'..." -ForegroundColor Cyan
Write-Host "Test Rounds: " -NoNewline

for ($i = 1; $i -le $r; $i++) {
    $output = & mvn clean test
    if ($LASTEXITCODE -eq 0) {
        Write-Host ("[$checkMark] ") -ForegroundColor Green -NoNewline
    } else {
        Write-Host ("[$crossMark] ") -ForegroundColor Red -NoNewline
        Write-Host "`nRound #$i failed. Maven [ERROR] output:" -ForegroundColor Yellow
        $output | Where-Object { $_ -match '\[ERROR\]' } | Write-Host
        $allPassed = $false
        break
    }
}

$stopwatch.Stop()
$elapsed = $stopwatch.Elapsed
$elapsedFormatted = $elapsed.ToString("hh\:mm\:ss")

if ($allPassed) {
    Write-Host "`n[All $r runs completed successfully. No tests failed.]" -ForegroundColor Green
}
Write-Host ("Total time elapsed: {0}" -f $elapsedFormatted) -ForegroundColor Cyan
