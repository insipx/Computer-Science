	.data
Ra:	.long	0
     .long 0
Rb:	.long	0
     .long 0
Rc:	.long	0
     .long 0
Rd:	.long	0
     .long 0
FP:	.long	0
     .long 0
Curr:	.long	0
     .long 0
Last:	.long	0
     .long 0
Line:	.ascii	"------------------------------- FP=%x, %d bytes\n"
	.asciz	"(EAX=%11d),(EBX=%11d),(ECX=%11d),(EDX=%11d)\n"
Format:	.asciz	"%8x: %8x(%11d)\n"
AddrFt: .asciz  "%8x: "
ValuFt: .asciz  "%8x(%11d)"
ComaFt: .asciz  ","
NLFt:   .asciz  "\n"
	.text
	.p2align 2
	.globl DumpS
	.type		 DumpS,@function
DumpS:	
	push	$0
	pop	%ecx
	movl	%ebp,FP
	movl	%esp,Curr

	movl	%eax,Ra		/* Save register values */
	movl	%ebx,Rb
	movl	%ecx,Rc
	movl	%edx,Rd

	push	%ebp
	movl	%esp,%ebp

	movl	8(%ebp),%ebx	/* The number of bytes to display */
	movl	%ebx,%ecx
	movl	Curr,%eax
	addl	%eax,%ebx	/* Compute the ending address */
	movl	%ebx,Last

	movl	FP,%eax		/* Print a header line */
	addl	$-4,%esp
	movl	Rd,%edx
	push	%edx
	movl	Rc,%edx
	push	%edx
	movl	Rb,%edx
	push	%edx
	movl	Ra,%edx
	push	%edx
	push	%ecx
	push	%eax
	push	$Line
	call	printf
	addl	$16,%esp

Loop:	movl	Curr,%eax	/* Iterate while Curr < Last */
	movl	Last,%ebx
	cmpl 	%eax,%ebx	
	jle  	Done
	movl	0(%eax),%ecx	/* Access memory for stact contents */

	push	%ecx		/* Print the stack element */
	push	%ecx		
	push	%eax

	push	$Format		/* Address and value */
	call 	printf
	addl	$16,%esp

	movl	Curr,%eax	/* Update Curr */
	addl	$4,%eax
	movl	%eax,Curr
	jmp	Loop
Done:	
	movl	Rd,%edx		/* Restore register values */
	movl	Rc,%ecx
	movl	Rb,%ebx
	movl	Ra,%eax
	leave
	ret
