import { Component, Input } from '@angular/core';
import { Comment } from '../../core/model/Comment.model';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss',
})
export class CommentComponent {
  @Input() commentData!: Comment;
}
