.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
   array1 DWORD 1, 2, 3, 4, 5
   array2 DWORD 6, 7, 8, 9, 10
   array3 DWORD LENGTHOF array1 dup(?)

.code
main proc


	invoke ExitProcess,0
main endp
end main