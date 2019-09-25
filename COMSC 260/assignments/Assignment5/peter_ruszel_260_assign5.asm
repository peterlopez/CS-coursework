.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	var1 BYTE 'A'
	var2 BYTE 'B'
	var3 BYTE 'C'
	var4 BYTE 'D'
	
.code
main proc
	
	invoke ExitProcess,0
main endp
end main