; Program template

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data

   msgforward WORD 6 DUP(?)
   msgbackward WORD 6 DUP(?)
   restore_esp DWORD ?

.code
main proc
  
  mov restore_esp, esp ; save the contents of register ESP so it can be restored before the program finishes
                       ; DO NOT REMOVE THIS

  ; clear the registers

  mov eax, 0
  mov ebx, 0
  mov ecx, 0
  mov edx, 0
  mov esi, 0
  mov edi, 0
  mov esp, 0
  mov ebp , 0

     ; store the message "Welcome Home" in reverse order across the six 16-bit registers

   mov ax, "!N"
   mov bx, "UF"
   mov si, " S"
   mov di, "I "
   mov sp, "SI"
   mov bp, "HT"


; Phase 1
; move letters from registers into array
; by incrementing the address offset of the array
; where each will be placed
   mov msgforward, bp
   mov [msgforward+2], sp
   mov [msgforward+4], di
   mov [msgforward+6], si
   mov [msgforward+8], bx
   mov [msgforward+10], ax



; Phase 2
; for this part, use CX and DX as temporary
; containers for letters. Then, swap each 8 byte letter
; across 6 registers where they are stored

; when swapping AX and BX we only need to use
; one 8-bit section of CX as a temporary container
; because we can address those registers at the 8-bit level
	; swap AX
	mov ch, al
	mov al, ah
	mov ah, ch

	; swap BX
	mov ch, bl
	mov bl, bh
	mov bh, ch

	; swap SI
	mov cx, [msgforward+6]
	mov dh, cl
	mov dl, ch
	mov si, dx

	; swap DI
	mov cx, [msgforward+4]
	mov dh, cl
	mov dl, ch
	mov di, dx

	; swap SP
	mov cx, [msgforward+2]
	mov dh, cl
	mov dl, ch
	mov sp, dx

	; swap BP
	mov cx, [msgforward]
	mov dh, cl
	mov dl, ch
	mov bp, dx




; Phase 3
; with the letters swapped in the 6 registers above,
; now we can create the message backwards array
	mov [msgbackward], ax
	mov [msgbackward+2], bx
	mov [msgbackward+4], si
	mov [msgbackward+6], di
	mov [msgbackward+8], sp
	mov [msgbackward+10], bp



mov esp, restore_esp ; restore register ESP to it's original value so the program can end correctly
                     ; DO NOT REMOVE THIS

	invoke ExitProcess,0
main endp
end main